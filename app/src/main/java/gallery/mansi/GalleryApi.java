package gallery.mansi;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class GalleryApi {
    private static final String key="6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";
    private static final String url="https://api.flickr.com/services/rest/";
    public static ImageService imageService=null;

    public static ImageService getImageService(){
        if(imageService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            imageService=retrofit.create(ImageService.class);
        }return imageService;
    }

    public interface ImageService{
        @GET("?method=flickr.photos.getRecent&api_key="+key)
        Call<ImageList> getImageList();

        /* @GET("image/id")
        Call<ImageList> getImageId(String id); */
    }
}