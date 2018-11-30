/*
 * Copyright 2011-2018 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sportsline

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class WebStartUp extends Simulation {

  val httpProtocol = http
    .baseUrl("https://localhost:8443/service/v1") // Here is the root for all relative URLs
//    .baseUrl("https://qa.sportsline.com/sportsline-web/service/v1") // Here is the root for all relative URLs
//    .baseUrl("https://dev.sportsline.com/sportsline-web/service/v1") // Here is the root for all relative URLs
//    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptHeader("application/json")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("webStartup") // A scenario is a chain of requests and pauses
    .exec(http("webstartup") // Here's an example of a POST request
      .get("/webStartup"))

//  setUp(scn.inject(rampUsers(100) during (300 seconds)).protocols(httpProtocol))
//  setUp(scn.inject(constantUsersPerSec(20) during (60 seconds)).protocols(httpProtocol))
  setUp(scn.inject(rampUsersPerSec(1) to (160) during (120 seconds)).protocols(httpProtocol))
}
