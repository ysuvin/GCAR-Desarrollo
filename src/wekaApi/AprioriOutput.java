package wekaApi;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class AprioriOutput {

 
  public static void apriori(String[] args) throws Exception {
	  
		   
		    String dataset ="src/weka.api/soybean.arff";
		    DataSource source = new DataSource(dataset);
		    Instances data = source.getDataSet();
		    Apriori resultado = new Apriori();
		    resultado.buildAssociations(data);
		    
		   
		    System.out.println(resultado); 
		  }
}