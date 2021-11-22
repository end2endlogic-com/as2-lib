/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 * Idea by: Sergey Yaskov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.as2demo.springboot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.helger.as2servlet.AS2WebAppListener;

@SpringBootApplication
public class As2SandboxApplication implements ServletContextListener
{
  public static void main (final String [] args)
  {
    SpringApplication.run (As2SandboxApplication.class, args);
  }

  @Override
  public void contextDestroyed (final ServletContextEvent sce)
  {
    ServletConfig.shutDown ();
    AS2WebAppListener.staticDestroy ();
  }
}
