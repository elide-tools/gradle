/*
 * Copyright 2023 the original author or authors.
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

package org.gradle.internal.component.external.model;

import org.gradle.api.artifacts.component.ModuleComponentIdentifier;
import org.gradle.internal.component.model.ComponentGraphResolveState;

import javax.annotation.concurrent.ThreadSafe;

/**
 * <p>Instances of this type are cached and reused for multiple graph resolutions, possibly in parallel. This means that the implementation must be thread-safe.
 */
@ThreadSafe
public interface ModuleComponentGraphResolveState extends ComponentGraphResolveState {
    @Override
    ModuleComponentIdentifier getId();

    // Try to avoid using this, this is here to allow migration away from ModuleComponentResolveMetadata
    ModuleComponentResolveMetadata getModuleResolveMetadata();
}
