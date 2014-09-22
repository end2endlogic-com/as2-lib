/**
 * The FreeBSD Copyright
 * Copyright 1994-2008 The FreeBSD Project. All rights reserved.
 * Copyright (C) 2013-2014 Philip Helger philip[at]helger[dot]com
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE FREEBSD PROJECT ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE FREEBSD PROJECT OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation
 * are those of the authors and should not be interpreted as representing
 * official policies, either expressed or implied, of the FreeBSD Project.
 */
package com.helger.as2lib.exception;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.as2lib.processor.IMessageProcessor;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.collections.ContainerHelper;
import com.helger.commons.lang.CGStringHelper;
import com.helger.commons.lang.StackTraceHelper;

public class ProcessorException extends OpenAS2Exception
{
  private final IMessageProcessor m_aProcessor;
  private final List <Throwable> m_aCauses;

  @Nonnull
  private static String _getMessage (@Nonnull @Nonempty final List <Throwable> aCauses)
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final Throwable aCause : aCauses)
      aSB.append ('\n').append (StackTraceHelper.getStackAsString (aCause));
    return aSB.toString ();
  }

  public ProcessorException (@Nonnull final IMessageProcessor aProcessor,
                             @Nonnull @Nonempty final List <Throwable> aCauses)
  {
    super ("Processor '" +
           CGStringHelper.getClassLocalName (aProcessor) +
           " threw exception(s):" +
           _getMessage (aCauses));
    ValueEnforcer.notNull (aProcessor, "Processor");
    ValueEnforcer.notEmptyNoNullValue (aCauses, "causes");

    m_aProcessor = aProcessor;
    m_aCauses = ContainerHelper.newList (aCauses);
  }

  @Nonnull
  public final IMessageProcessor getProcessor ()
  {
    return m_aProcessor;
  }

  @Nonnull
  @Nonempty
  public final List <Throwable> getCauses ()
  {
    return ContainerHelper.newList (m_aCauses);
  }
}
