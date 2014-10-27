/*
 * Copyright 2014 Yubico.
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://developers.google.com/open-source/licenses/bsd
 */

package com.yubico.u2f.data.messages;

import com.google.common.base.Objects;
import com.yubico.u2f.data.messages.json.JsonObject;
import com.yubico.u2f.exceptions.U2fException;

import static com.google.common.base.Preconditions.checkNotNull;

public class RegisterResponse extends JsonObject {
  /** websafe-base64(raw registration response message) */
  private final String registrationData;

  /** websafe-base64(UTF8(stringified(client data))) */
  private final String clientData;

  public RegisterResponse(String registrationData, String clientData) {
    this.registrationData = checkNotNull(registrationData);
    this.clientData = checkNotNull(clientData);
  }

  public String getRegistrationData() {
    return registrationData;
  }

  public ClientData getClientData() throws U2fException {
    return new ClientData(clientData);
  }

  public static RegisterResponse fromJson(String json) {
    return GSON.fromJson(json, RegisterResponse.class);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(registrationData, clientData);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof RegisterResponse))
      return false;
    RegisterResponse other = (RegisterResponse) obj;
    return Objects.equal(clientData, other.clientData)
            && Objects.equal(registrationData, other.registrationData);
  }
}
