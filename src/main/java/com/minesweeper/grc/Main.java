package com.minesweeper.grc;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@OpenAPIDefinition(info = @Info(title = "Campo Minado", version = "1.0"))
@QuarkusMain
@ApplicationPath("/")
public class Main extends Application {

	public static void main(String... args) {
		Quarkus.run(MyApp.class, args);
	}

	public static class MyApp implements QuarkusApplication {

		@Override
		public int run(String... args) throws Exception {

			Quarkus.waitForExit();
			return 0;
		}
	}
}
