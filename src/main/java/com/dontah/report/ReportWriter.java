package com.dontah.report;

import com.dontah.processors.BoostAnalizer;
import com.dontah.service.Result;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by Bruno on 20/07/14.
 */
public class ReportWriter {

    public ReportWriter(List<Result> results, List<BoostAnalizer> boostAnalizers)
    {
        try
        {
            Velocity.init(System.class.getResource("/velocity.properties").getPath());

            VelocityContext context = new VelocityContext();
            context.put("results", results);
            context.put("boostAnalizers", boostAnalizers);

            Template template =  null;

            String name = System.class.getResource("/template.vm").getPath();
            try
            {
                template = Velocity.getTemplate("./src/main/resources/template.vm");
            }
            catch( ResourceNotFoundException rnfe )
            {
                System.out.println("Example : error : cannot find template " + name );
            }
            catch( ParseErrorException pee )
            {
                System.out.println("Example : Syntax error in template " + name + ":" + pee );
            }

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("build/bastterResult.html"));

            if ( template != null)
                template.merge(context, writer);

            writer.flush();
            writer.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

}
