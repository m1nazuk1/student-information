package filters

import javax.inject.Inject
import play.api.http.HttpFilters
import play.filters.cors.CORSFilter
import play.filters.csrf.CSRFFilter
import play.filters.headers.SecurityHeadersFilter

class Filters @Inject() (
    corsFilter: CORSFilter,
    securityHeadersFilter: SecurityHeadersFilter
) extends HttpFilters {
    override val filters = Seq(corsFilter, securityHeadersFilter)
}
