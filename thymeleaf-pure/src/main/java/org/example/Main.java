package org.example;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class Main {

  public static void main(String[] args) {
    ITemplateEngine engine = createTemplateEngine();

    Context ctx = new Context();
    ctx.setVariable("name", "Dimo");

    String html = engine.process("test.html", ctx);
    System.out.println(html);
  }

  private static ITemplateEngine createTemplateEngine() {
    TemplateEngine templateEngine = new TemplateEngine();

    templateEngine.setTemplateResolver(new ClassLoaderTemplateResolver());

    return templateEngine;
  }
}