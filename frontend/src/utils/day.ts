export const diffDay = (target:string)=>{
    const targetDate = +new Date(target)
    const today = +new Date();

    const diff = targetDate - today

    return Math.floor(diff/(1000*60*60*24))

}

export const diffDayOfStartEnd =(startDate:Date ,endDate:Date):number=>{
    const diff = +endDate-+startDate

    return Math.floor(diff/(1000*60*60*24))

}