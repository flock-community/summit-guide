package agent

import community.flock.aigentic.core.dsl.agent
import community.flock.aigentic.core.tool.Parameter
import community.flock.aigentic.core.tool.ParameterType
import community.flock.aigentic.core.tool.PrimitiveValue
import community.flock.aigentic.core.tool.Tool
import community.flock.aigentic.core.tool.ToolName
import community.flock.aigentic.core.tool.getStringValue
import community.flock.aigentic.openai.dsl.openAIModel
import community.flock.aigentic.openai.model.OpenAIModelIdentifier
import kotlinx.serialization.json.JsonObject

class SummitGuide {

    val agent = agent {
        task("Summarize the newsfeed of the day and determine the sentiment") {
            addInstruction("Analyze the sentiment for each news event")
            addInstruction("Save the sentiment for each news event")
        }
        openAIModel {
            apiKey("<insert your OpenAI API key here>")
            modelIdentifier(OpenAIModelIdentifier.GPT4O)
        }
        addTool(weatherTool)
    }

    val weatherTool =
        object : Tool {

            override val name = ToolName("weatherTool")
            override val description = "Get weather for location"

            val locationParameter = Parameter.Primitive(
                name = "LocationName",
                description = "The name of the climbingArea",
                isRequired = true,
                type = ParameterType.Primitive.String
            )

            val countryParameter = Parameter.Primitive(
                name = "CountryName",
                description = "The name of the country",
                isRequired = true,
                type = ParameterType.Primitive.String
            )

            val dateParameter = Parameter.Primitive(
                name = "Date",
                description = "The date to retrieve the weather for",
                isRequired = true,
                type = ParameterType.Primitive.String
            )

            override val parameters = listOf(locationParameter, countryParameter, dateParameter)

            override val handler: suspend (JsonObject) -> String = { arguments ->

                val location = locationParameter.getStringValue(arguments)
                val country = countryParameter.getStringValue(arguments)
                val date = dateParameter.getStringValue(arguments)



                "Saved successfully: '$title' with sentiment: $sentiment"
            }
        }

}