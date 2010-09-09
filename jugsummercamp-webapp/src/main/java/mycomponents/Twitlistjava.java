
        package mycomponents;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import twitter4j.*;

        import javax.faces.component.NamingContainer;
        import javax.faces.component.UIData;
        import javax.faces.component.html.HtmlOutputText;
        import javax.faces.context.FacesContext;
        import javax.faces.model.DataModel;
        import javax.faces.model.ListDataModel;
        import java.io.IOException;


        public class Twitlistjava extends UIData implements NamingContainer{

            public String getFamily() {return "javax.faces.NamingContainer";}

            private final static Logger LOGGER = LoggerFactory.getLogger(Twitlistjava.class);

            private HtmlOutputText pseudo;

            private UIData data;

            @Override
            public void encodeBegin(FacesContext context) throws IOException {
                DataModel dataModel = new ListDataModel();
                try {
                    QueryResult result = new TwitterFactory().getInstance().search(new Query("from:"+getAttributes().get("qui")));
                    dataModel.setWrappedData(result.getTweets());
                    data.setValue(dataModel);
                } catch (TwitterException e) {
                    LOGGER.error("erreur lors de l'appel a twitter {}", e.getLocalizedMessage());
                }
                super.encodeBegin(context);
            }

            public UIData getData() {
                return data;
            }

            public void setData(UIData data) {
                this.data = data;
            }

            public HtmlOutputText getPseudo() {
                return pseudo;
            }

            public void setPseudo(HtmlOutputText pseudo) {
                this.pseudo = pseudo;
            }
        }
