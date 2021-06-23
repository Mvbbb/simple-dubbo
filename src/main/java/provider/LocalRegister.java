package provider;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地注册:维护从服务名到实现类的映射
 */
public class LocalRegister {
    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, Class implClass){
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
