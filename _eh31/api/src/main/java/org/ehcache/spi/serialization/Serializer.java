/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehcache.spi.serialization;

import java.nio.ByteBuffer;

/**
 * Defines the contract used to transform type instances to and from a serial form.
 * <P>
 *   Implementations must be thread-safe.
 * </P>
 * <P>
 * When used within the default serialization provider, there are additional requirements.
 * The implementations must define either or both of the two constructors:
 * <dl>
 *   <dt><code><i>Serializer</i>(ClassLoader loader)</code>
 *   <dd>This constructor is used to initialize the serializer for transient caches.
 *   <dt><code><i>Serializer</i>(ClassLoader loader, org.ehcache.spi.persistence.StateRepository stateRepository)</code>
 *   <dd>This constructor is used to initialize the serializer for persistent caches and allows them to store any relevant
 *   state in the provided repository.
 * </dl>
 * The {@code ClassLoader} value may be {@code null}.  If not {@code null}, the class loader
 * instance provided should be used during deserialization to load classes needed by the deserialized objects.
 * </P>
 * <p>
 *   The serialized object's class must be preserved; deserialization of the serial form of an object must
 *   return an object of the same class. The following contract must always be true:
 *   <p>
 *   <code>object.getClass().equals( mySerializer.read(mySerializer.serialize(object)).getClass() )</code>
 *   </p>
 * </p>
 *
 * @param <T> the type of the instances to serialize
 *
 * @see SerializationProvider
 */
public interface Serializer<T> {

  /**
   * Transforms the given instance into its serial form.
   *
   * @param object the instance to serialize
   *
   * @return the binary representation of the serial form
   *
   * @throws SerializerException if serialization fails
   */
  ByteBuffer serialize(T object) throws SerializerException;

  /**
   * Reconstructs an instance from the given serial form.
   *
   * @param binary the binary representation of the serial form
   *
   * @return the de-serialized instance
   *
   * @throws SerializerException if reading the byte buffer fails
   * @throws ClassNotFoundException if the type to de-serialize to cannot be found
   */
  T read(ByteBuffer binary) throws ClassNotFoundException, SerializerException;

  /**
   * Checks if the given instance and serial form {@link Object#equals(Object) represent} the same instance.
   *
   * @param object the instance to check
   * @param binary the serial form to check
   *
   * @return {@code true} if both parameters represent equal instances, {@code false} otherwise
   *
   * @throws SerializerException if reading the byte buffer fails
   * @throws ClassNotFoundException if the type to de-serialize to cannot be found
   */
  boolean equals(T object, ByteBuffer binary) throws ClassNotFoundException, SerializerException;

}