package com.alfresco.generate.report;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

public class GenerateDatasource
{
  public JRDataSource datasource()
  {
    JREmptyDataSource jrEmptyDataSource = new JREmptyDataSource();
    
    return jrEmptyDataSource;
  }
}
