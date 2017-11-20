package io.mtso.app;

import io.javalin.Javalin;
import io.javalin.Handler;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Handler serveIndex = (ctx) -> {
        MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions
        //options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        Node document = parser.parse("This is *Sparta*");
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"

        ctx.header("Content-Type", "text/html");
        ctx.result(html);
    };

    public static void main( String[] args )
    {
        Javalin app = Javalin.start(3000);
        app.get("/", serveIndex);
        app.get("/greet/:name", (ctx) -> {
            ctx.result("Hello, " + ctx.param("name") + "!");
        });
        app.get("/*", (ctx) -> {
            ctx.result("404");
        });
        System.out.println( "Listening on 3000" );
    }
}
