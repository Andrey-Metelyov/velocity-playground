import org.apache.velocity.Template;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.StringReader;

public class App {
    public static void main(String[] args) throws ParseException {
        RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
        String s = "Hello ${user} my name is ${system}.name! #if (${system.acitive.since()}) $system.date getBankName(${bank}) $callcenter #end";
        StringReader reader = new StringReader(s);
        Template template = new Template();
        SimpleNode node = runtimeServices.parse(reader, template);

        printNodes(node);

    }

    private static void printNodes(Node node) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node child = node.jjtGetChild(i);
            if (child instanceof ASTReference ref) {
                System.out.println(child.getClass() + "-" + child.getType() + "-" + child.literal());
                System.out.println(ref);
//                System.out.println(child);
            }
            printNodes(child);
        }
    }
}
