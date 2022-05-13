import xyz.rh.kt.Player
import java.util.concurrent.CopyOnWriteArrayList

/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val alist1 = ArrayList<Player>()
    alist1.add(Player("xl",15))
    alist1.add(Player("hy",13))
    alist1.add(Player("dodo",11))

    val cwlist1 = CopyOnWriteArrayList<Player>()
    cwlist1.add(Player("gyou",29))
    cwlist1.add(Player("qiqi",2))
    cwlist1.add(Player("qiaoling",14))

    cwlist1.sortBy { it.age }

    cwlist1.addAll(alist1)

    println("cwlist1.size=${cwlist1.size}")

    for (value in cwlist1) {
        println(value)
    }
    cwlist1.sortBy { it.name }

    for (value in cwlist1) {
        println("After sort :: $value")
    }




}