/*
 *    Copyright 2010 The myBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.guice.datasource.helper;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;

/**
 * 
 *
 * @version $Id$
 */
final class KeyResolver implements Provider<String> {

    private final Key<String> key;

    private final String defaultValue;

    private final String toString;

    @Inject
    private Injector injector;

    public KeyResolver(final String key, final String defaultValue) {
        this.key = Key.get(String.class, Names.named(key));
        this.defaultValue = defaultValue;
        this.toString = "${" + key + "}";
    }

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    /**
     * {@inheritDoc}
     */
    public String get() {
        try {
            return this.injector.getInstance(this.key);
        } catch (Throwable e) {
            if (this.defaultValue != null) {
                return this.defaultValue;
            }
            return this.toString;
        }
    }

    @Override
    public String toString() {
        return this.toString;
    }

}