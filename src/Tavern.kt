import kotlin.math.roundToInt
const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10

fun main() {
    placeOrder("Shandy,DrAgOn's BrEAth,5.91")
}


fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("지갑 전체 금액: 금화 $totalPurse")

    if (totalPurse >= price) {
        println("금화 $price 로 술을 구입함")

        val remainingBalance = totalPurse - price
        println("남은 잔액: ${"%.2f".format(remainingBalance)}")

        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
        displayBalance()
    } else {
        val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
        val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
        print("$tavernMaster : 잔액이 부족해 구입할 수 없습니다.")
    }
}


private fun displayBalance() {
    println("플레이어의 지갑 잔액 : 금화: $playerGold 개, 은화: $playerSilver 개")
}


private fun toDragonSpeak(pharse: String) =
    pharse.replace(Regex("[aeiouAEIOU]")) {
        when (it.value) {
            "a", "A" -> "4"
            "e", "E" -> "3"
            "i", "I" -> "1"
            "o", "O" -> "0"
            "u", "U" -> "|_|"
            else -> it.value
        }
    }


private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("마드리갈은 $tavernMaster 에게 주문한다.")

    val (type, name, price) = menuData.split(',')
    val message = "마드리갈은 금화 $price 로 $name ($type)를 구입한다."
    println(message)

    performPurchase(price.toDouble())

    val phrase = if (name == "DrAgOn's BrEAth") {
        println("마드리갈이 감탄한다: ${toDragonSpeak("와, $name 진짜 좋구나!")}")
    } else {
        "마드리갈이 말한다: 감사합니다 $name."
    }

    println(phrase)
}



