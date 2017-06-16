import java.util.TimeZone.getAvailableIDs


//function 1
val result = getAvailableIDs.grouped(10).map(_.head).filter(_.contains("/")).map(_.split("/")(1)).toList






















