package de.htw.ai.busbunching.transformer

import com.fasterxml.jackson.databind.ObjectMapper
import de.htw.ai.busbunching.model.Route
import de.htw.ai.busbunching.model.route.RouteFactory
import spark.ResponseTransformer

class GeoJsonTransformer extends ResponseTransformer {

	private val objectMapper = new ObjectMapper()

	override def render(o: scala.Any): String = {
		if (o.isInstanceOf[Route]) {
			val route = o.asInstanceOf[Route]
			val geoJson = RouteFactory.getHandler(route.getRouteType).convertToGeoJson(route)
			objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(geoJson)
		} else {
			"Invalid Data"
		}
	}
}