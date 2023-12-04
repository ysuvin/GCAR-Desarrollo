package beans;

/// No se utiliza este archivo.

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.FileUtils;
import util.DataSourceUtils;

import java.io.Serializable;
import java.io.File;
//import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
//import weka.associations.Apriori;
//import weka.core.Instance;
import weka.core.Instances;
//import weka.core.converters.ConverterUtils.DataSource;

//import util.Analisis;

@ManagedBean(name="dtGestionAnalisisBean")
@ViewScoped
public class GestionAnalisisBean implements Serializable{

		
		

//		  public void apriori(String[] args) throws Exception {
//		    // cargar datos
//		    Instances data = DataSource.read("src/weka.api/soybean.arff"); // falta direcion del dataset dentro de la app
//		    data.setClassIndex(data.numAttributes() - 1);
	//
//		    // construir asociacion
//		    Apriori apriori = new Apriori();
//		    apriori.setClassIndex(data.classIndex());
//		    apriori.buildAssociations(data);
	//
//		    // salida asociacion
//		    System.out.println(apriori); // enviar datos a vista estadisticas
//		  }
	//	
	//	
//		  public  void bayesNet(String args[]) throws Exception {
//			    // train classifier
//			    BayesNet cls = new BayesNet();
//			    Instances data = DataSource.read("src/weka.api/soybean.arff");
//			    data.setClassIndex(data.numAttributes() - 1);
//			    cls.buildClassifier(data);
//			    
//			    System.out.println(cls);
//		  }
		
		  public void naiveBayes() throws Exception{
			  
			  System.out.println("Se accedio al metodo naiveBayes");
			  
			// se encarga de traer el fichero
			  FileUtils fileUtils = new FileUtils(); 
		      File file = fileUtils.getFile("iris.arff");
		      //System.out.println("p1");
		  
		    // trata la fuente y crea la instancia con el dataset
		      DataSourceUtils dataSourceUtils = new DataSourceUtils();
		      Instances instances = dataSourceUtils.newWekaInstances(file); 
		      System.out.println("########   DATASET   #########"+ "\n");
		      System.out.println(instances + "\n");
		    	 
			// se crea el objeto de la tecnica de mineria NaiveBayes  
			  NaiveBayes resultado = new NaiveBayes(); 
			
			  //  se genera el clasificador 
			  resultado.buildClassifier(instances); 
			  System.out.println("########   RESULTADO   #########" + "\n");
			  System.out.println(resultado.toString());
			
			 
		  }
		
		

	}
