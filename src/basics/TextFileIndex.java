package basics;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.codecs.simpletext.SimpleTextCodec;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;



public class TextFileIndex {
	
	public static void main(String args[]) throws IOException{
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_45);
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_45,analyzer);
		conf.setCodec(new SimpleTextCodec());
		File simpleText = new File("simpletext");
		SimpleFSDirectory directory = new SimpleFSDirectory(simpleText);
		//Let's write to disk so that we can see what it looks like
		IndexWriter w = new IndexWriter(directory, conf);
		addDoc(w, "Lucene in Action", "193398817");
		addDoc(w, "Lucene for Dummies", "55320055Z");
		addDoc(w, "Managing Gigabytes", "55063554A");
		addDoc(w, "The Art of Computer Science", "9900333X");
		w.close();
		
		
	}
	
	private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
		  Document doc = new Document();
		  doc.add(new TextField("title", title, Field.Store.YES));
		  doc.add(new StringField("isbn", isbn, Field.Store.YES));
		  w.addDocument(doc);
		}
		 
	

}
