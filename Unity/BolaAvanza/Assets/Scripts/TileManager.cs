using UnityEngine;
using System.Collections;

public class TileManager : MonoBehaviour {
    public GameObject CurrentTile;
    public GameObject TilePrefabs;
    public int baldosas;
    public static bool inicial;
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
        Invoke("iniciar", 0.5f);
        
    }

    public void newTile()
    {
        //este metodo es llamado desde Start
        //tambien desde el método TileScript
        //de los prefabs
        int rand = Random.Range(0, 4);
        switch (rand)
        {
            case 0:
                CurrentTile = (GameObject)Instantiate(TilePrefabs, CurrentTile.transform.GetChild(0).transform.GetChild(0).transform.position + new Vector3(0, 0, 1f), Quaternion.identity);
                break;
            case 1:
                CurrentTile = (GameObject)Instantiate(TilePrefabs, CurrentTile.transform.GetChild(0).transform.GetChild(0).transform.position + new Vector3(0, 0, -0.25f), Quaternion.identity);
                break;
            case 2:
                CurrentTile = (GameObject)Instantiate(TilePrefabs, CurrentTile.transform.GetChild(0).transform.GetChild(1).transform.position + new Vector3(-1f,0), Quaternion.identity);
                break;
            case 3:
                CurrentTile = (GameObject)Instantiate(TilePrefabs, CurrentTile.transform.GetChild(0).transform.GetChild(1).transform.position + new Vector3(0.25f, 0), Quaternion.identity);
                break;
        }
/*        if (rand == 0)
        {
            CurrentTile = (GameObject)Instantiate(TilePrefabs, CurrentTile.transform.GetChild(0).transform.GetChild(0).transform.position + new Vector3(0, 0,-0.5f), Quaternion.identity);
            Debug.Log(CurrentTile.transform.GetChild(0).transform.GetChild(0).gameObject.name);
        }
        else
        {
            CurrentTile = (GameObject)Instantiate(TilePrefabs, CurrentTile.transform.GetChild(0).transform.GetChild(1).transform.position, Quaternion.identity);
            Debug.Log(CurrentTile.transform.GetChild(0).transform.GetChild(1).gameObject.name);
        }*/
        if (Random.Range(0, 100) < 8)
        {
            CurrentTile.transform.GetChild(1).gameObject.SetActive(true);
        }
        
         //genera un aleatorio 0,1
        //instancia un prefab y lo asocia a CurrentTile
        //mediante (GameObject)Instantiate
        //usamos transform.GetChild para pillar la
        //position de los transform hijos de CurrentTile



    }
    void iniciar()
    {
        if (TileManager.inicial)
        {
            for (int i = 0; i < baldosas; i++)
            {
                newTile();
            }
            TileManager.inicial = false;
        }
    }
}
