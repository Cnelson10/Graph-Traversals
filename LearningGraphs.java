/* Christopher Nelson
// 21 April 2018
// Assignment 09: Learning Graphs & Assignment 10: Dijkstra's Algorithm
*/
package learninggraphs;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import javax.swing.JFrame;


public class LearningGraphs {

    public static void main(String[] args) {
        
        Graph <Integer, String> g = new UndirectedSparseGraph <>();
        
        g.addEdge("A", 0, 1);
        g.addEdge("B", 0, 3);
        g.addEdge("C", 1, 4);
        g.addEdge("D", 1, 6);
        g.addEdge("E", 1, 7);
        g.addEdge("F", 1, 2);
        g.addEdge("G", 3, 2);
        g.addEdge("H", 2, 9);
        g.addEdge("I", 2, 8);
        g.addEdge("J", 4, 7);
        g.addEdge("K", 4, 6);
        g.addEdge("L", 4, 5);
        g.addEdge("M", 6, 7);
        
        ArrayList<Integer> vertices = new ArrayList<>();
        for(Integer V : g.getVertices()){
            vertices.add(V);
        }
        int start = vertices.get(0);
        
        System.out.println(g);
        System.out.println("\nThis is the Breadth-First Traversal of the graph starting at vertex " + start + ":");
        BFS(g, start);
        System.out.println("\nThis is the Depth-First Traversal (Discovery Order) of the graph starting at vertex " + start + ":");
        DFS(g, start);
        
        
        Graph <Integer, Double> wg = new UndirectedSparseGraph <>();

        wg.addEdge(4.0, 0, 1);
        wg.addEdge(1.0, 0, 3);
        wg.addEdge(3.0, 1, 4);
        wg.addEdge(3.1, 1, 6);
        wg.addEdge(1.1, 1, 7);
        wg.addEdge(0.9, 1, 2);
        wg.addEdge(1.2, 3, 2);
        wg.addEdge(2.9, 2, 9);
        wg.addEdge(2.0, 2, 8);
        wg.addEdge(0.8, 4, 7);
        wg.addEdge(1.3, 4, 6);
        wg.addEdge(2.1, 4, 5);
        wg.addEdge(1.9, 6, 7);
        
        ArrayList<Integer> wvertices = new ArrayList<>();
        for(Integer V : wg.getVertices()){
            wvertices.add(V);
        }
        int wstart = vertices.get(0);
        
        System.out.println("\nThis is Dijkstra's Algorithm to find ther shortest path to all the vertices starting from vertex " + wstart + ":");
        Dijkstra(wg, wstart);
    }

    public static void BFS (Graph<Integer, String> g, int start){
        
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        List<Integer> output = new LinkedList<>();
        
        seen.add(start);
        q.offer(start);
        
        
        while (!q.isEmpty()){
            
            int u = q.poll();
            for(Integer v : g.getNeighbors(u)){
                if (!output.contains(v) && !seen.contains(v)){
                    seen.add(v);
                    q.offer(v);
                    
                }
            }
            if(!output.contains(u)){
                output.add(u);
            }
            
            
        }
        System.out.println(output);
    } 
    
    public static void DFS (Graph<Integer, String> g, int start){
        
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> s = new Stack<>();
        List<Integer> output = new LinkedList<>();
        
        s.push(start);
        
        while (!s.isEmpty()){
            
            int u = s.pop();
            output.add(u);
            if(!seen.contains(u)){
                seen.add(u);
                for(Integer v : g.getNeighbors(u)){
                    if(!seen.contains(v) && !s.contains(v)){
                        s.push(v);
                    }
                }    
            }  
        }
        System.out.println(output);
        
    }
    
    public static void Dijkstra (Graph<Integer, Double> wg, int start){
        HashSet <Integer> done = new HashSet <>();
        HashSet <Integer> todo = new HashSet <>();
        HashMap <Integer, Double> dist = new HashMap <>();
        HashMap <Integer, Integer> pred = new HashMap <>();
        
        done.add(start);
        
        ArrayList<Integer> vertices = new ArrayList<>();
        for(Integer V : wg.getVertices()){
                vertices.add(V);
        }
        for(int i = 1; i < vertices.size();i++){
            todo.add(vertices.get(i));
        }
        
        for (Integer V : todo){
            pred.put(V, start);
            if(wg.isNeighbor(start, V)){
                dist.put(V, wg.findEdge(start, V));
                
            }else{
                dist.put(V, Double.POSITIVE_INFINITY);
                
            }
        }
        
        while(!todo.isEmpty()){
            
            double smallest = Integer.MAX_VALUE;
            int toDone = 0;
            for(Integer u : todo){
                if (dist.get(u) < smallest){
                    smallest = dist.get(u);
                    toDone = u;
                }
            }
            
            done.add(toDone);
            todo.remove(toDone);
            
            for(Integer v : wg.getNeighbors(toDone)){
                if(todo.contains(v)){
                    if(dist.get(toDone) + wg.findEdge(toDone, v) < dist.get(v)){
                        dist.replace(v, dist.get(toDone) + wg.findEdge(toDone, v));
                        pred.replace(v, toDone);
                        
                    }
                }
            }
            System.out.println(done);
            System.out.println(todo);
            System.out.println(pred);
            System.out.println(dist);
            
        }
    }   
}
