using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LvlController : MonoBehaviour {
    public GameManager gm;
    public float segundos;

    // Use this for initialization
    void Start () {
        gm = GameObject.FindWithTag("GameController").GetComponent<GameManager>();
        segundos = 20;
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void OnTriggerEnter(Collider other)
    {
        if(other.name != "ataqueDemon_0(Clone)") Debug.Log("fin nivel "+other.name);
        if (other.name == "MC")
        {
            Debug.Log("nuevo nivel "+gm.name);
            //gUI.iniciarCuentaAtras(segundos);
            Destroy(this.gameObject, segundos);
            gm.nextLevel();
        }
    }

    public LvlController generarNivel(int posicion)
    {
        Debug.Log("inicia");
        int rand = Random.Range(1, 4);
        string path = "Prefabs/lvl_0" + rand;
        //string path = "Prefabs/Demon";
        Debug.Log(path);
        //carga en un objeto prefab el recurso indicado por el path
        GameObject prefab = Resources.Load(path) as GameObject;

        float posY = 8.5f * posicion;
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
        prefab.GetComponent<LvlController>().gm = gm;
        return prefab.GetComponent<LvlController>();
    }

}
