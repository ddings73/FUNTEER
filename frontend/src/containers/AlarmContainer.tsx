import { EventListener, EventSourcePolyfill, MessageEvent } from "event-source-polyfill";
import { useEffect, useState } from "react";
import { useEffectOnce } from "usehooks-ts";
import { http } from "../api/axios";

export function AlarmContainer() {
    const eventListType={
        content:String,
        alarmId:String,
        userEmail:String,
      }
    const token = localStorage.getItem('accessToken');
    const [listening, setListening] = useState(false);
    const [sseData, setSseData] = useState({});
    const [respon, setRespon] = useState(false);
    const [eventList, setEventList] = useState<typeof eventListType[]>();
    let eventSource: EventSourcePolyfill | undefined;
    
    
    useEffectOnce(()=>{
        if(token){
          requestGetAlarms();
          requestDeleteAlarm();
        }
      })
    
    const requestGetAlarms = async () => {
        setEventList([]);
        try {
          const response = await http.get('subscribe/alarm');
          // console.log(response);
          setEventList(response.data);
    
          response.data.forEach((event:typeof eventListType)=>{
            alert(event.content);
          })
          
        } catch (error) {
          console.error(error);
        }
    };
    
      const requestDeleteAlarm=async()=>{
        await http.delete(`subscribe/alarm`)
        console.log('success')
      }
    
    // sse
    useEffect(() => {

        // token = localStorage.getItem('accessToken');
        if (!listening && token && !eventSource) {
        // sse 연결
        // http://localhost:8080/api/v1/subscribe
        // https://i8e204.p.ssafy.io/api/v1/subscribe
            eventSource=new EventSourcePolyfill("http://localhost:8080/api/v1/subscribe",{
                headers:{
                "Content-Type":"text/event-stream",
                "Access-Control-Allow-Origin": "*",
                "Authorization":`Bearer ${token}`,
                "Cache-Control": "no-cache",
                },
                heartbeatTimeout: 86400000,
                withCredentials: true,
            });

            console.log(eventSource);
            
            // 최초 연결
            eventSource.onopen = (event) => {
                setListening(true);
            };

            // 서버에서 메시지 날릴 때
            eventSource.onmessage = (event) => {
                setSseData(event.data);
                setRespon(true);
                console.log(event.data);
                console.log('onmessage');
                if (event.data !== undefined) alert(event.data);
            };

          

            eventSource.addEventListener('sse', ((event: MessageEvent) => {
                console.log(event);
                // alert(event.data);
                if(!event.data.includes('Created')){
                alert(event.data);
                }
                requestDeleteAlarm();
                
            }) as EventListener);
        } 
        
        else if(!token){
            console.log('logout');
            eventSource?.close();
        }
        return () => {
            if (!token && eventSource !== undefined) {
                eventSource.close();
                setListening(false);
            }
        }
    },[token]);


    return (
        <div>{token}</div>
    )
}
export default AlarmContainer ;