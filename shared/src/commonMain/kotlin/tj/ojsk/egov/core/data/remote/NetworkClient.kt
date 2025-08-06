package tj.ojsk.egov.core.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.TimeoutCancellationException
import tj.ojsk.egov.core.constants.BASE_URL
import tj.ojsk.egov.core.data.local.manager.TokenManager
import tj.ojsk.egov.platform.Logger
import tj.ojsk.egov.platform.isDebugBuild

class NetworkClient(
    val httpClient: HttpClient,
    private val tokenManager: TokenManager,
) {

    suspend inline fun <reified T> get(
        endpoint: String,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        authorized: Boolean = true
    ): NetworkResult<T> = safeRequest("GET", endpoint, authorized = authorized) {
        httpClient.get {
            url {
                takeFrom(BASE_URL + endpoint)
                queryParams.forEach { (k, v) -> parameters.append(k, v) }
            }
            setupHeaders(headers, authorized)
        }.body()
    }
    suspend inline fun <reified T, reified Body> post(
        endpoint: String,
        body: Body? = null,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        authorized: Boolean = true
    ): NetworkResult<T> = safeRequest("POST", endpoint, logBody = body, authorized = authorized) {
        httpClient.post {
            url {
                takeFrom(BASE_URL + endpoint)
                queryParams.forEach { (k, v) -> parameters.append(k, v) }
            }

            setupHeaders(headers, authorized)

            if (body != null) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        }.body()
    }

    suspend inline fun <reified T, reified Body> put(
        endpoint: String,
        body: Body? = null,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        authorized: Boolean = true
    ): NetworkResult<T> = safeRequest("PUT", endpoint, logBody = body, authorized = authorized) {
        httpClient.put {
            url {
                takeFrom(BASE_URL + endpoint)
                queryParams.forEach { (k, v) -> parameters.append(k, v) }
            }
            setupHeaders(headers, authorized)
            contentType(ContentType.Application.Json)
            body?.let { setBody(it) }
        }.body()
    }

    suspend inline fun <reified T> patch(
        endpoint: String,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        authorized: Boolean = true
    ): NetworkResult<T> = safeRequest("PATCH", endpoint, authorized = authorized) {
        httpClient.patch {
            url {
                takeFrom(BASE_URL + endpoint)
                queryParams.forEach { (k, v) -> parameters.append(k, v) }
            }
            setupHeaders(headers, authorized)
        }.body()
    }

    suspend inline fun <reified T> delete(
        endpoint: String,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        authorized: Boolean = true
    ): NetworkResult<T> = safeRequest("DELETE", endpoint, authorized = authorized) {
        httpClient.delete {
            url {
                takeFrom(BASE_URL + endpoint)
                queryParams.forEach { (k, v) -> parameters.append(k, v) }
            }
            setupHeaders(headers, authorized)
        }.body()
    }

    suspend fun HttpRequestBuilder.setupHeaders(
        customHeaders: Map<String, String>,
        authorized: Boolean
    ) {
        customHeaders.forEach { (key, value) -> header(key, value) }
        if (authorized) {
            tokenManager.getAccessToken()?.let { token ->
                header(HttpHeaders.Authorization, "Bearer $token")
            }
        }
    }

    suspend fun <T> safeRequest(
        method: String,
        endpoint: String,
        logBody: Any? = null,
        authorized: Boolean = true,
        block: suspend () -> T
    ): NetworkResult<T> {
        val fullUrl = BASE_URL + endpoint
        val tag = "NetworkClient"

        try {
            if (isDebugBuild) {
                Logger.d(tag, "→ [$method] $fullUrl")
                logBody?.let { Logger.d(tag, "Request Body: $it") }
            }

            val response = block()

            if (isDebugBuild) {
                Logger.d(tag, "← [$method] $fullUrl: SUCCESS")
                Logger.d(tag, "Response: $response")
            }

            return NetworkResult.Success(response)

        } catch (e: ClientRequestException) {
            if (e.response.status == HttpStatusCode.Unauthorized && authorized) {
                Logger.e(tag, "401 Unauthorized. Попытка обновить токен…")
                val retryResult = tryRefreshTokenAndRetry(block)
                if (retryResult != null) return retryResult
            }
            Logger.e(tag, "← [$method] $fullUrl: HTTP ERROR - ${e.response.status.value} ${e.message}")
            return NetworkResult.HttpError(e.response.status.value, e.message)
        } catch (e: ResponseException) {
            Logger.e(tag, "← [$method] $fullUrl: HTTP ERROR - ${e.response.status.value} ${e.message}")
            return NetworkResult.HttpError(e.response.status.value, e.message)
        } catch (e: IOException) {
            Logger.e(tag, "← [$method] $fullUrl: NETWORK ERROR - ${e.message}")
            return NetworkResult.NetworkError(e)
        } catch (e: TimeoutCancellationException) {
            Logger.e(tag, "← [$method] $fullUrl: TIMEOUT ERROR - ${e.message}")
            return NetworkResult.NetworkError(e)
        } catch (e: Exception) {
            Logger.e(tag, "← [$method] $fullUrl: UNKNOWN ERROR - ${e.message ?: e::class.simpleName}")
            return NetworkResult.NetworkError(e)
        }
    }

    private suspend fun <T> tryRefreshTokenAndRetry(
        block: suspend () -> T
    ): NetworkResult<T>? {
        val refreshToken = tokenManager.getRefreshToken() ?: return null

        return try {
            //пока что заглушка, но в реальной реализации нужно будет получить новый токен через refresh_token

            Logger.d("TokenManager", "Обновляем токен через refresh_token...")
            tokenManager.deleteToken()
            null
        } catch (e: Exception) {
            Logger.e("TokenManager", "Ошибка при обновлении токена: ${e.message}")
            null
        }
    }
}