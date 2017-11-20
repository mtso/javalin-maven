package io.mtso.app;

import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Javalin app = Javalin.start(3000);
        app.get("/", ctx -> ctx.result("Hello World"));
        System.out.println( "Listening on 3000" );
    }
}
