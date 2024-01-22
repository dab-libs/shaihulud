package ru.dab.shaihulud.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Map;

public class MustacheGenerator implements Generator {
    @Override
    public void generate(@NotNull Map<String, Object> specification,
                         @NotNull TemplateBundle templateBundle,
                         @NotNull ResultStore store,
                         @Nullable Map<String, Object> options)
            throws IOException {
        Writer writer = store.getWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        //Mustache mustache = mf.compile("", "example");
        //mustache.execute(writer, specification);
        writer.flush();
    }
}
