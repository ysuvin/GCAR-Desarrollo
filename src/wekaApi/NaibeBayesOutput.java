package wekaApi;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class NaibeBayesOutput {

	
	public void naiveBayes(String args []) throws Exception{
		  
		  DataSource ds = new DataSource("src/weka.api/soybean.arff");
		  Instances ins = ds.getDataSet();
		  ins.setClassIndex(3);
		  NaiveBayes nb = new NaiveBayes();
		  nb.buildClassifier(ins);
		  System.out.println(nb.toString());
		  
		  
	  }
	
	
	
	
}
