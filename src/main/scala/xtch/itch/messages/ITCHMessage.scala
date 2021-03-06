/*
 * Copyright 2010 the original author or authors.
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
package xtch.itch.messages

import java.nio.ByteBuffer
import xtch.itch.elements.ASCII
import xtch.itch.templates.ITCHTemplate

object ITCHMessage extends ASCII {
  val terminator = "\r\n".getBytes(charset)
}

case class ITCHMessage(template: ITCHTemplate) extends Message with ASCII {
  import ITCHMessage._
  override def encode(buffer: ByteBuffer) {
    super.encode(buffer)
    template.encode(buffer, this)
    buffer.put(terminator)
  }
  override def length = super.length + terminator.length
}
