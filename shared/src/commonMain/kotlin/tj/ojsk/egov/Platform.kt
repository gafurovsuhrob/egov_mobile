package tj.ojsk.egov

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform