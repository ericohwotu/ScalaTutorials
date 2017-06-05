import java.util.TimeZone.getAvailableIDs
//getAvailableIDs.grouped(5).toArray

//function 1
getAvailableIDs.filter(_.contains("/")).map(value => value.split("/")(1)).take(20)






