package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanTreeMap {
    private final Map<Integer, String> codeBySymbol = new HashMap<>();

    public HuffmanTreeMap(HuffmanInfo root) {
        if (root == null) {
            throw new IllegalArgumentException("root no puede ser null");
        }
        buildCodeTable(root);
    }

    public String get(int b) {
        return codeBySymbol.get(b);
    }

    private void buildCodeTable(HuffmanInfo root) {
        Deque<NodePath> stack = new ArrayDeque<>();
        stack.push(new NodePath(root, ""));

        while (!stack.isEmpty()) {
            NodePath np = stack.pop();
            HuffmanInfo p = np.node;
            String path = np.path;

            boolean isLeaf = p.getLeft() == null && p.getRight() == null;

            if (isLeaf) {
                String code = path.isEmpty() ? "0" : path;
                codeBySymbol.put(p.getC(), code);
            } else {
                if (p.getRight() != null) {
                    stack.push(new NodePath(p.getRight(), path + "1"));
                }
                if (p.getLeft() != null) {
                    stack.push(new NodePath(p.getLeft(), path + "0"));
                }
            }
        }
    }

    // ✅ Devuelve una lista con los pares símbolo-código (HuffmanTable)
    public List<HuffmanTable> toTableList() {
        List<HuffmanTable> table = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : codeBySymbol.entrySet()) {
            table.add(new HuffmanTable(entry.getKey(), entry.getValue()));
        }
        return table;
    }

    public int[] keys() {
        return codeBySymbol.keySet().stream().mapToInt(Integer::intValue).toArray();
    }

    public int size() {
        return codeBySymbol.size();
    }

    // Clase interna auxiliar
    private static final class NodePath {
        final HuffmanInfo node;
        final String path;

        NodePath(HuffmanInfo node, String path) {
            this.node = node;
            this.path = path;
        }
    }
}
