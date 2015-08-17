/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Moises Urias
 */
public class Automata {
    
    private ArrayList<Nodo> estados = new ArrayList<Nodo>(); 
    //private ArrayList<Automata>automatas = new ArrayList<Automata>(); /*Arreglo de automatas*/
    private String simbolo;
    public ArrayList<Transicion> transiciones = new ArrayList<Transicion>();

    public Stack<Automata> automatas = new Stack<Automata>();
    /**
     * Constructor de la clase automata sin parametros
     */
    public Automata(){
        
       
        
    }
    
    /**
     * Constructor de la clase automata.<p>
     * Crea un automata sencillo basado en el simbolo del automata.<p>
     * Los DOS nodos del automata se definen como NO inciales y NO finales.
     * @param simbolo 
     */
    public Automata(String simbolo) {
        System.out.println("Se crea un nuevo automata");
        /*Se crean los nodos que tendra el automata*/
        Nodo nodoA = new Nodo(true, false); /*Se crea un nuevo nodo inicial*/
        Nodo nodoB = new Nodo(false, true); /*Se crea un nuevo nodo final*/
        
        
       
        this.simbolo = simbolo; /*Simbolo del automata y de la transicion*/
        this.transiciones.add(new Transicion(nodoA.getId(),simbolo,nodoB.getId()));
 
        System.out.println(transiciones.get(0).toString());
        
        this.estados.add(nodoA);
        this.estados.add(nodoB);
        
    }
    
    /**
     * Crea un automata en base a dos automatas proporcionados.<p>
     * Se usa para la expresion OR entre dos automatas
     * @param automata1
     * @param automata2 
     */
    private Automata OR(Automata automata1, Automata automata2)
    {
        int cont =0; /*Contador*/
        Automata automata = new Automata(); /*Automata que contenra */
        
        Nodo nodoI = new Nodo(); /*Se crea un nodo inicial*/
        Nodo nodoF = new Nodo(); /*Se crea un nodo final*/
        
        
        /*Se recorre el primer automata*/
        while (cont<automata1.estados.size())
            {
                /*Debug
                System.out.println("\nDEBUG OR\nEl tamaño del atuomata1 es: " + 
                        automata1.transiciones.size() +  
                        "\nLa transaccion del auomata1 es: " + "\n"+
                        "La cantidad de transacciones del automata1 es: " +
                        automata1.transiciones.get(0));*/
                
                
                /*Nodo provisional para nodos de los automatas*/
                Nodo estado = automata1.estados.get(cont);
                
                if (estado.iseInicial() == true)
                {
                    
                    
                    automata.transiciones.add(new Transicion(nodoI.getId(),"!",estado.getId()));
                   
                    
                    System.out.println(automata.transiciones.get(0).toString()); /*DEBUG*/
                    
                    estado.seteInicial(false);
                    automata1.estados.set(cont, estado);
                }
                
                if (estado.iseFinal()==true)
                {
                    
                    automata.transiciones.add(new Transicion(estado.getId(),"!",nodoF.getId()));
                     
                    
                    System.out.println(automata.transiciones.get(1).toString()); /*DEBUG*/
                    
                    estado.seteFinal(false);
                    automata1.estados.set(cont, estado);
                }
                
                cont++;
                
            }
        
        /*Se reinicia el contador a cero*/
        cont=0;
       
        System.out.println("El tamaño del segundo automata es: " + automata2.estados.size());
        
        /*Se recorren los estados del segundo automata*/
        while (cont<automata2.estados.size())
            {
                
                /*Debug
                System.out.println("\nDEBUG OR\nEl tamaño del atuomata2 es: " + 
                        automata2.transiciones.size() +  
                        "\n"+
                        "La cantidad de transacciones del automata2 es: " +
                        automata2.transiciones.get(0));*/
                
                /*Nodo provisional para nodos de los automatas*/
                Nodo estado = automata2.estados.get(cont);
                
                if (estado.iseInicial() == true)
                {
                    automata.transiciones.add(new Transicion(nodoI.getId(),"!",estado.getId()));
                    
                   
                    System.out.println(automata.transiciones.get(2).toString()); /*DEBUG*/
                    
                    estado.seteInicial(false);
                    automata2.estados.set(cont, estado);
                }
                
                if (estado.iseFinal()==true)
                {
                    automata.transiciones.add(new Transicion(estado.getId(),"!",nodoF.getId()));
                    
                    
                    System.out.println(automata.transiciones.get(3).toString()); /*DEBUG*/
                    
                    estado.seteFinal(false);
                    automata2.estados.set(cont, estado);
                }
                
                cont++;
                
            }
        
        
        cont =0; /*Reinicia el contrador a cero*/
        
        /*Asigna todos los nodos del automata1 al nuevo automata*/
        while(cont<automata1.estados.size())
        {
            automata.estados.add(automata1.estados.get(cont));
            cont++;
        }
        
        
        cont=0; /*Reinicia el contador a cero*/
        
        /*Asignna todos los nodos del automata2 al nuevo automata*/
        while(cont<automata2.estados.size())
        {
            automata.estados.add(automata2.estados.get(cont));
            cont++;
            
        }
        
        cont=0; /*Reinicia el contrador a cero*/
        
        /*Asinga las transiciones del atuomata1 al nuevo automata*/
        while(cont<automata1.transiciones.size())
        {
            automata.transiciones.add(automata1.transiciones.get(cont));
            cont++; //Se suma uno al contador
        }
        
        /*Se reinicia a cero el contrador*/
        cont = 0;
        
        /*Asina las transiciones del automata2 al nuevo automata*/
        while(cont<automata2.transiciones.size()){
            automata.transiciones.add(automata2.transiciones.get(cont));
            cont++;
        }
        
        /*se asignna un nuevo nodo inicial al automata*/
        nodoI.seteInicial(true);
        automata.estados.add(nodoI);
        
        /*Se asigna un nuevo nodo final al automata*/
        nodoF.seteFinal(true);
        automata.estados.add(nodoF);
    
        /*Devuelve el automata*/
        return automata;
    }
    
    /**
     * Este constructor crea un automata basandose en el simbolo cuando se <p>
     * quiere especificar si los nodos del automata son iniciales o finales.
     * 
     * @param simbolo
     * @param einicial1
     * @param efinal1
     * @param einicial2
     * @param efinal2 
     */
    public Automata(String simbolo, boolean einicial1, boolean efinal1,
            boolean einicial2, boolean efinal2)
    {
        this.simbolo = simbolo;
        this.estados.add(new Nodo(einicial1,efinal1));
        this.estados.add(new Nodo(einicial2, efinal2));
    }
    
    
    
    
    /**
     * Este metodo se utiliza para crear un nuevo estado del automata
     * @param simbolo
     * @param einicial
     * @param efinal 
     */
    public void crearEstado(String simbolo, boolean einicial, boolean efinal)
    {
      this.estados.add(new Nodo(einicial, efinal));
    }
        
    /**
     * Crea un automata basado en el simbolo que se ingrese
     * @param simbolo 
     */
    public void crearAutomata(String simbolo){      
        
        /*Compara el simbolo para saber si es parte del alfabeto*/
       boolean comparar = esAlfabeto(simbolo);
       
       if (comparar == true){
           automatas.push(new Automata(simbolo));
        }
       else
       {
           
           /*Si se encuentra un OR*/
           if (simbolo.equals("|"))
           {
               /*Se sacan dos automatas de la pila*/
               Automata automata1 = automatas.pop();
               Automata automata2 = automatas.pop();
               
               automatas.push(this.OR(automata1, automata2));
               
           }
           else if(simbolo.equals("*"))
           {
               Automata automata1 = automatas.pop();
               automatas.push(this.kleene(automata1));
               
           }
           else if(simbolo.equals("."))
           {
               Automata automata1 = automatas.pop();
               Automata automata2 = automatas.pop();
               automatas.push(this.concatenar(automata1, automata2));
           }
           else
           {
               /*Programacion defensiva*/
               System.out.println("La expesion regular esta mal ingresada, el"
                       + "simbolo: ' " +simbolo+"'"+
                       "no es reconocible");
               
           }
       }
        
    }
    
    
    
    /**
     * Esta funcion sirve para saber si un simbolo de la cadena de texto leida<p>
     * es parte del alfabeto o es un operador
     * 
     * @param simbolo
     * @return simbolo
     */
    private boolean esAlfabeto(String simbolo)
    {
        String[] operadores = {"(","|","?",".","*","+","^"};
        boolean operador = true; /*Si se ha reconocido como operador o no*/
        
        
        int i = 0; /*Contador de operadores*/
        
        while (i<operadores.length)
        {
           if (simbolo.equals(operadores[i]))
           {
               operador = false;
           }
           
           i++; /*Aumenta uno el contador 'operador'*/
            
        }
        
        /*Devuelve el resultado de evaluar el simbolo para saber si es un 
            operador o no*/
        return operador;
    }
    
    
    /**
     * Devuevelve una automata despues de aplicarle la operacion kleene al 
     *  automata ingresado
     * @param automata1
     * @return 
     */
    private Automata kleene(Automata automata1)
    {
        System.out.println("Se hacer un kleene");
        Automata automata = automata1;
        
        Nodo nodoI = new Nodo(); /*Nuevo nodo inicial*/
        Nodo nodoF = new Nodo(); /*Nuevo nodo final*/
        
        int cont=0; /*Contador*/
        
        /*Se crean tempralmente estados iniciales y finales*/
        Nodo tEstadoInicial = null; 
        Nodo tEstadoFinal = null;
        
        /*Se recorre el automata para encontrar el estado inicial*/
        while (cont<automata1.estados.size())
        {
            Nodo estado=automata1.estados.get(cont);
            if (estado.iseInicial()==true)
            {
                /*Se crea la transicion entre el nodo inicial del kleene y el 
                  nodo inicial del automata que se esta analizando*/
                automata.transiciones.add(new Transicion(nodoI.getId(),"!",estado.getId()));
                System.out.println(automata.transiciones.get(automata.transiciones.size()-1));
                tEstadoInicial = estado;
                estado.seteInicial(false);
                
                /*Se le quita la propiedad de estado inicial*/
                automata.estados.set(cont, estado);
            }
            
            if (estado.iseFinal() == true)
            {
                automata.transiciones.add(new Transicion(estado.getId(),"!",nodoF.getId()));
                System.out.println(automata.transiciones.get(automata.transiciones.size()-1));

                tEstadoFinal = estado;
                estado.seteFinal(false);
                
                /*Se le quita la propiedad de estado final*/
                automata.estados.set(cont, estado);
            }
            
            cont++;
        }
        
        /*Se crea una transicion entre el estado final y el estado inicial 
            del automata original*/
            automata.transiciones.add(new Transicion(tEstadoFinal.getId(),"!",tEstadoInicial.getId()));
            System.out.println(automata.transiciones.get(automata.transiciones.size()-1));
            
            /*Se agrga la transicion epsilon entre el primer nuevo estado 
            inicial y el nuevo estado final*/
            automata.transiciones.add(new Transicion(nodoI.getId(),"!",nodoF.getId()));
            System.out.println(automata.transiciones.get(automata.transiciones.size()-1));
            /*Se agregan los nuevos nodos al automata*/
            
            nodoF.seteFinal(true);
            nodoI.seteInicial(true);
            
            automata.estados.add(nodoF);
            automata.estados.add(nodoI);
        
            /*Deuvelve el automata generado*/
        return automata;
    }

    
    /**
     * Concatena dos automatas
     * @param automata2
     * @param automata1
     * @return automata1 concatenado con automata2
     */
    private Automata concatenar(Automata automata2, Automata automata1) {
        /*******************************************************************/
        
        System.out.println("Se encontro una concatenacion");
               
        /********************************************************************/
        
        int cont=0; /*Contador*/
        System.out.println("El valor del contador es: " + cont);
        System.out.println("La cantidad de estados del automata 1 es: " + automata1.estados.size());
        while(cont<automata1.estados.size())
        {
          Nodo nodo = automata1.estados.get(cont);
          
          if (nodo.iseFinal()==true){
              System.out.println("El nodo final " + nodo.getId());
              /*Se recorre el otro automata para buscar su estado inicial
                 y concatenarlos*/
              for(int i =0; i<automata2.estados.size();i++){
                  Nodo nodo2 = automata2.estados.get(i);
                  
                  if (nodo2.iseInicial()==true)
                  {
                      System.out.println("El nodo inicial es: " + nodo2.getId());
                      /*Se crea una transicion entre ambos automatas */
                      nodo2.seteInicial(false);
                      automata1.transiciones.add(new Transicion(nodo.getId(),"!",nodo2.getId()));
                      System.out.println(automata1.transiciones.get(automata1.transiciones.size()-1));
                      nodo.seteFinal(false);
                      
                      break;
                  }
              }
                  
          }  
          
          cont++;
          
           
          
        }
        
        /*Se agrega al automata1 todos los nodos y transiciones del automata2*/
        for(int i=0;i<automata2.estados.size();i++)
          {
            automata1.estados.add(automata2.estados.get(i));
          }
        
        /*Se agregan todas las transiciones del automata2 al automata1*/
        for(int i=0;i<automata2.transiciones.size();i++)
        {
            automata1.transiciones.add(automata2.transiciones.get(i));
        }
        
        return automata1;
    }

    /**
     * Devuelve un ArrayList con las transiciones del automata
     * @return 
     */
    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    /**
     * Devuelve un ArrayList con los estados del automata
     * @return 
     */
    public ArrayList<Nodo> getEstados() {
        return estados;
    }
    
    
    
    
}