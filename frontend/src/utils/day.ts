export const diffDay = (target:string)=>{
    const targetDate = +new Date(target)
    const today = +new Date();

    const diff = targetDate - today

    return Math.floor(diff/(1000*60*60*24))

}