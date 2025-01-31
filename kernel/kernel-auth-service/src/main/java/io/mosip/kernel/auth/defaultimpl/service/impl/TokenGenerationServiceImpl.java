/**
 * 
 */
package io.mosip.kernel.auth.defaultimpl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import io.mosip.kernel.auth.defaultimpl.service.TokenGenerationService;
import io.mosip.kernel.core.authmanager.model.AuthNResponseDto;
import io.mosip.kernel.core.authmanager.model.ClientSecret;
import io.mosip.kernel.openid.bridge.api.service.AuthService;

/**
 * @author Ramadurai Pandian
 *
 */

@Component
public class TokenGenerationServiceImpl implements TokenGenerationService {

	@Lazy
	@Autowired
	AuthService authService;

	@Value("${mosip.kernel.auth.app.id}")
	private String authAppId;

	@Value("${mosip.kernel.auth.client.id}")
	private String clientId;

	@Value("${mosip.kernel.auth.secret.key}")
	private String secretKey;

	@Value("${mosip.kernel.ida.app.id}")
	private String idaAppId;

	@Value("${mosip.kernel.ida.client.id}")
	private String idaClientId;

	@Value("${mosip.kernel.ida.secret.key}")
	private String idaSecretKey;

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.mosip.kernel.auth.service.TokenGenerationService#
	 * getInternalTokenGenerationService()
	 */
	@Override
	public String getInternalTokenGenerationService() throws Exception {
		ClientSecret clientSecret = new ClientSecret();
		clientSecret.setAppId(authAppId);
		clientSecret.setClientId(clientId);
		clientSecret.setSecretKey(secretKey);
		AuthNResponseDto authNResponseDto = authService.authenticateWithSecretKey(clientSecret);
		return authNResponseDto.getToken();
	}

	@Override
	public String getUINBasedToken() throws Exception {
		ClientSecret clientSecret = new ClientSecret();
		clientSecret.setAppId(idaAppId);
		clientSecret.setClientId(idaClientId);
		clientSecret.setSecretKey(idaSecretKey);
		AuthNResponseDto authNResponseDto = authService.authenticateWithSecretKey(clientSecret);
		return authNResponseDto.getToken();
	}

}
