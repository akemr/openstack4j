package org.openstack4j.connectors.jersey2;

import static org.openstack4j.core.transport.ClientConstants.CONTENT_TYPE_IMAGE_V2_PATCH;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.openstack4j.model.image.v2.ImageUpdate;

@Provider
@Produces(CONTENT_TYPE_IMAGE_V2_PATCH)
public class ImageUpdateMessageBodyWriter implements MessageBodyWriter<ImageUpdate> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return ImageUpdate.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(ImageUpdate imageUpdate, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(ImageUpdate imageUpdate, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream out) throws IOException, WebApplicationException {

        Writer writer = new PrintWriter(out);
        writer.write(new ObjectMapper().writeValueAsString(imageUpdate));

        writer.flush();
        writer.close();
    }
}
