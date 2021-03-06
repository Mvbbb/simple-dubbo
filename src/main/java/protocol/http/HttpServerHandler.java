package protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;
import provider.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp){
        
        // 处理 Consumer 的请求
        try {
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) objectInputStream.readObject();
            Class aClass = LocalRegister.get(invocation.getInterfaceName());
            Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String invoke = (String) method.invoke(aClass.newInstance(), invocation.getParams());
            IOUtils.write(invoke, resp.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
