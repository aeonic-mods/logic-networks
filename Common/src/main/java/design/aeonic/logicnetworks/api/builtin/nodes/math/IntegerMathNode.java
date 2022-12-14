package design.aeonic.logicnetworks.api.builtin.nodes.math;

import design.aeonic.logicnetworks.api.builtin.BuiltinSignalTypes;
import design.aeonic.logicnetworks.api.core.Translations;
import design.aeonic.logicnetworks.api.logic.network.NodeType;
import design.aeonic.logicnetworks.api.logic.network.node.base.SimpleOperatorNode;
import net.minecraft.network.chat.Component;

import java.util.UUID;

public abstract class IntegerMathNode extends SimpleOperatorNode<IntegerMathNode> {
    public IntegerMathNode(NodeType<IntegerMathNode> nodeType, Component name, UUID uuid, int x, int y) {
        super(nodeType, name, BuiltinSignalTypes.INTEGER.arrayOf(2), BuiltinSignalTypes.INTEGER.arrayOf(), uuid, x, y);
    }

    @Override
    public int getHeight() {
        return 19;
    }

    @Override
    public final Object[] evaluate(Object[] inputs) {
        return new Object[]{evaluateInternal((Integer) inputs[0], (Integer) inputs[1])};
    }

    public abstract Integer evaluateInternal(Integer a, Integer b);

    public static class Max extends IntegerMathNode {
        public Max(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_MAX, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return Math.max(a, b);
        }
    }

    public static class Min extends IntegerMathNode {
        public Min(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_MIN, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return Math.min(a, b);
        }
    }

    public static class Add extends IntegerMathNode {
        public Add(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_ADD, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a + b;
        }
    }

    public static class Subtract extends IntegerMathNode {
        public Subtract(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_SUBTRACT, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a - b;
        }
    }

    public static class Multiply extends IntegerMathNode {
        public Multiply(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_MULTIPLY, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a * b;
        }
    }

    public static class Divide extends IntegerMathNode {
        public Divide(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_DIVIDE, uuid, x, y);
        }

        @Override
        public boolean validate(Object[] inputs) {
            return super.validate(inputs) && !inputs[1].equals(0);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a / b;
        }
    }

    public static class Modulo extends IntegerMathNode {
        public Modulo(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_MODULO, uuid, x, y);
        }

        @Override
        public boolean validate(Object[] inputs) {
            return super.validate(inputs) && !inputs[1].equals(0);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a % b;
        }
    }

    public static class Exponent extends IntegerMathNode {
        public Exponent(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.INTEGER_EXPONENT, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return (int) Math.pow(a, b);
        }
    }

    public static class BitwiseNot extends IntegerMathNode {
        public BitwiseNot(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.BITWISE_NOT, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return ~a;
        }
    }

    public static class BitwiseAnd extends IntegerMathNode {
        public BitwiseAnd(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.BITWISE_AND, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a & b;
        }
    }

    public static class BitwiseOr extends IntegerMathNode {
        public BitwiseOr(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.BITWISE_OR, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a | b;
        }
    }

    public static class BitwiseXor extends IntegerMathNode {
        public BitwiseXor(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.BITWISE_XOR, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return a ^ b;
        }
    }

    public static class BitwiseNand extends IntegerMathNode {
        public BitwiseNand(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.BITWISE_NAND, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return ~(a & b);
        }
    }

    public static class BitwiseNor extends IntegerMathNode {
        public BitwiseNor(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.BITWISE_NOR, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return ~(a | b);
        }
    }

    public static class BitwiseXnor extends IntegerMathNode {

        public BitwiseXnor(NodeType<IntegerMathNode> nodeType, UUID uuid, int x, int y) {
            super(nodeType, Translations.Nodes.BITWISE_XNOR, uuid, x, y);
        }

        @Override
        public Integer evaluateInternal(Integer a, Integer b) {
            return ~(a ^ b);
        }
    }
}
