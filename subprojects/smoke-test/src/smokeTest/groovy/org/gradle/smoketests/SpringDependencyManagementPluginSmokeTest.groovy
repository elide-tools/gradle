/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.smoketests

import spock.lang.Ignore
import spock.lang.Issue

class SpringDependencyManagementPluginSmokeTest extends AbstractPluginValidatingSmokeTest {
    @Ignore("Spring dependency management plugin needs to be updated to handle removal of deprecated Upload task")
    @Issue('https://plugins.gradle.org/plugin/io.spring.dependency-management')
    def 'spring dependency management plugin'() {
        given:
        buildFile << """
            plugins {
                id 'java'
                id 'io.spring.dependency-management' version '${TestedVersions.springDependencyManagement}'
            }

            ${mavenCentralRepository()}

            dependencyManagement {
                dependencies {
                    dependency 'org.springframework:spring-core:4.0.3.RELEASE'
                    dependency group: 'commons-logging', name: 'commons-logging', version: '1.1.2'
                }
            }

            dependencies {
                implementation 'org.springframework:spring-core'
            }
            """.stripIndent()

        when:
        def result = runner("dependencies", "--configuration", "compileClasspath").build()

        then:
        result.output.contains('org.springframework:spring-core -> 4.0.3.RELEASE')
    }

    @Override
    Map<String, Versions> getPluginsToValidate() {
        [
            // TODO: restore this once a version of the plugin is released that doesn't use Upload, version 1.0.12.RELEASE still requires it
            // 'io.spring.dependency-management' : Versions.of(TestedVersions.springDependencyManagement)
        ]
    }
}
