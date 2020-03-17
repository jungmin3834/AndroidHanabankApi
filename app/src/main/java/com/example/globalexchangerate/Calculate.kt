package com.example.globalexchangerate

class Calculate(){
    fun calculateMoneyRate(money: String, r: Float?): String {
        var m = StringBuffer(money)
        var rate: Int
        if (r != null) {
            var ratestr = StringBuffer("")
            var count = 0
            for (s in r.toString()) {
                if (s != '.') {
                    ratestr.append(s)
                    count++
                } else
                    count = 0
            }
            if (count == r.toString().length)
                count = 0
            rate = ratestr.toString().toInt()

            var num = 0
            for (i in m.length - 1 downTo 0) {
                num += (m[i] - '0') * rate
                m.setCharAt(i,if (num > 9) (num % 10 + '0'.toInt()).toChar() else (num + '0'.toInt()).toChar())
                num /= 10
            }

            while (num > 0) {
                m.insert(0, (num % 10 + '0'.toInt()).toChar())
                num /= 10
            }
            m.insert(m.length - count, '.')
            if(m.length - count  > 2)
                m.setLength((m.length - count )+ 2)
            return m.toString()
        }
        return ""
    }
}
