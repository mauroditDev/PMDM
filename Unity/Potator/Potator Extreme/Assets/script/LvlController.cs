using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LvlController : MonoBehaviour {
    public GameManager gm;

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void OnTriggerEnter(Collider other)
    {
        if(other.name == "MC")
        {
            gm.nextLevel();
        }
    }

    public void generarNivel(int posicion)
    {
        Debug.Log("inicia");
        int rand = Random.Range(1, 3);
        string path = "Prefabs/lvl_0" + rand;
        //string path = "Prefabs/Demon";
        Debug.Log(path);
        //carga en un objeto prefab el recurso indicado por el path
        GameObject prefab = Resources.Load(path) as GameObject;

        float posY = 7.5f * posicion;
        Debug.Log("nombre:"+prefab.name);
        //Lo instancia en el 0,0,0
        GameObject nivelObject = (GameObject)Instantiate(prefab, Vector3.zero+ new Vector3(0,posY), Quaternion.identity);

        for(int i = 0; i < nivelObject.transform.childCount; i++)
        {
            if(nivelObject.transform.GetChild(i).name == "Demon" || nivelObject.transform.GetChild(i).name == "MaloAvanza" )
            {
                gm.enemigos.Add(nivelObject.transform.GetChild(i).gameObject);
                if (nivelObject.transform.GetChild(i).name == "Demon")
                {
                    nivelObject.transform.GetChild(i).gameObject.GetComponent<DemonController>().gm = this.gm;
                }
            }
            
        }


    }

}
