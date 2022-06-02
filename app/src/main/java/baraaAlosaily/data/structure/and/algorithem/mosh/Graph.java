package baraaAlosaily.data.structure.and.algorithem.mosh;

import java.util.*;

public class Graph {
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private Map<String,Node> nodes = new HashMap<>();
    private Map<Node,List<Node>> addjacencyList  = new HashMap<>();

    public void addNode(String label){
        var node=new Node(label);
        nodes.putIfAbsent(label,node);
        addjacencyList.putIfAbsent(node,new ArrayList<>());
    }

    public void addEdge(String from,String to){
        var fromNode =nodes.get(from);
        if(fromNode==null)
            throw new IllegalArgumentException("from node not found");

        var toNode =nodes.get(to);
        if(toNode==null)
            throw new IllegalArgumentException("to node not found");

        if(fromNode==toNode)
            throw new IllegalArgumentException("you can't create edge from node to the same node");
        addjacencyList.get(fromNode).add(toNode);
    }

    public void print(){
        for (var source: addjacencyList.keySet()) {
            var target= addjacencyList.get(source);
            if (!target.isEmpty()){
                System.out.println(source+" is connected to "+target);
            }
        }
    }

    public void removeNode(String label){
        var node=nodes.get(label);
        if(node==null)
            return;

        for (var n:addjacencyList.keySet()) {
            addjacencyList.get(n).remove(node);
        }
        addjacencyList.remove(node);
        nodes.remove(node);
    }
    public void removeEdge(String from,String to){
        var fromNode =nodes.get(from);
        if(fromNode==null)
            throw new IllegalArgumentException("from node not found");

        var toNode =nodes.get(to);
        if(toNode==null)
            throw new IllegalArgumentException("to node not found");

        if(fromNode==toNode)
            throw new IllegalArgumentException("you can't create edge from node to the same node");

        addjacencyList.get(fromNode).remove(toNode);


    }

    public void traverseDepthFirstRec(String root){
        var node=nodes.get(root);
        if(node==null){
            return;
        }
        traverseDepthFirstRec(node,new HashSet<>());
    }
    private void traverseDepthFirstRec(Node root, Set<Node> visited){
        System.out.println(root);
        visited.add(root);

        for (var node:addjacencyList.get(root)) {
            if(!visited.contains(node))
                traverseDepthFirstRec(node,visited);
        }
    }


    public void traverseDepthFirst(String root){
        var node=nodes.get(root);
        if(node==null){
            return;
        }

        Set<Node> visited=new HashSet<>();
       Stack<Node> stack=new Stack<>();
       stack.push(node);

       while (!stack.isEmpty()){
           var current=stack.pop();
           if(visited.contains(current))
               continue;
           System.out.println(current);
           visited.add(current);

           for (var neighbour:addjacencyList.get(current)){
               if(!visited.contains(neighbour))
                   stack.push(neighbour);
           }
       }
    }
//    private void traverseDepthFirst(Node root, Set<Node> visited){
//        System.out.println(root);
//        visited.add(root);
//
//        for (var node:addjacencyList.get(root)) {
//            if(!visited.contains(node))
//                traverseDepthFirst(node,visited);
//        }
//    }

     public void traverseBreadthFirst(String root){
        var node=nodes.get(root);
        if(node==null)
            return;

        Set<Node> visited=new HashSet<>();
        Queue<Node> queue=new ArrayDeque<>();

        queue.add(node);

         while (!queue.isEmpty()){
             var current=queue.remove();
             if(visited.contains(current))
                 continue;
             System.out.println(current);
             visited.add(current);

             for (var neighbor:addjacencyList.get(current)) {
                 if(!visited.contains(neighbor)){
                     queue.add(neighbor);
                 }
             }
         }
     }
}
