println("hello world")

def fn : Int => String = (x) => if (x>=5 && x<=10){"don't get at me bro!"}else if(x>10){"you are a DEAD man!"}else{"you are smart!"}

fn(2)
fn(5)
fn(6)
fn(12)