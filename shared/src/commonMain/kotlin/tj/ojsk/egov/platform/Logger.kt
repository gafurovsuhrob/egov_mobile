package tj.ojsk.egov.platform

expect object Logger {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String)
}
