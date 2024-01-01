package joryu.sns_service.utils

import jakarta.servlet.http.HttpServletRequest

class IpUtils {
    companion object {
        fun getClientIp(request: HttpServletRequest): String? {
            var ip: String? = request.getHeader("X-Forwarded-For")?.trim()
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("x-real-ip")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("x-original-forwarded-for")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("Proxy-Client-IP")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("WL-Proxy-Client-IP")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("HTTP_X_FORWARDED")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("HTTP_CLIENT_IP")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("HTTP_FORWARDED_FOR")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("HTTP_FORWARDED")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("HTTP_VIA")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.getHeader("REMOTE_ADDR")
            }
            if (ip.isNullOrEmpty()) {
                ip = request.remoteAddr
            }
            return ip
        }
    }
}
