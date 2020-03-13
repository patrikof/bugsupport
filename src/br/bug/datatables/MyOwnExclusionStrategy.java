package br.bug.datatables;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;

public class MyOwnExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> classe) {
		// TODO Auto-generated method stub
		
		 
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes classe) {
		// TODO Auto-generated method stub
		
		final Expose expose = classe.getAnnotation(Expose.class);
        return expose != null && !expose.serialize();
		
/*		String className = classe.getDeclaringClass().getName();
		String fieldName = classe.getName();
		
			
		
		return 
	               className.equals(Produto.class.getName())
	                && (fieldName.equals("versoes") || fieldName.equals("clientes"));*/
	}
	
	
	
	

	    
	

}
