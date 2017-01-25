using UnityEngine;
using System.Collections;

public class TileManager : MonoBehaviour {
    public GameObject CurrentTile;
    public GameObject[] TilePrefabs;
    public int baldosas;
    private static TileManager instance;
    public static TileManager Instance
    {
        get
        {
            if (instance == null)
            {
                instance = GameObject.FindObjectOfType<TileManager>();
            }
            return instance;
        }
    }
   
    void Start()
    {
        //Se crean diez baldosas aleatorias iniciales
        //llamando a CrearBaldosa()
        for(int i = 0; i<baldosas; i++)
        {
            newTile();
        }
    }


    public void newTile()
    {
        //este metodo es llamado desde Start
        //tambien desde el método TileScript
        //de los prefabs
        if (Random.Range(0, 1) < 0.5)
        {
            CurrentTile = (GameObject)Instantiate(TilePrefabs[0], CurrentTile.transform.GetChild(0).transform);
        }
        else
        {
            CurrentTile = (GameObject)Instantiate(TilePrefabs[1], CurrentTile.transform.GetChild(0).transform);
        }
        //genera un aleatorio 0,1
        //instancia un prefab y lo asocia a CurrentTile
        //mediante (GameObject)Instantiate
        //usamos transform.GetChild para pillar la
        //position de los transform hijos de CurrentTile



    }
}
