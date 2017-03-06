using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LvlController : MonoBehaviour {
    public GameManager gm;
    public static double ordenes;
    public double orden;
    public bool fichado;
    private float diferencia;
    //public float segundos;

    // Use this for initialization
    void Start () {
        gm = GameObject.FindWithTag("GameController").GetComponent<GameManager>();
        fichado = false;
        double aux = transform.position.y / 8.5;
        if (aux > ordenes)
        {
            ordenes = aux;
        }
        orden = aux;
        Debug.Log(aux+" = "+ transform.position.y / 8.5);
    }
	
	// Update is called once per frame
	void Update () {
        diferencia = gm.masterChief.transform.position.y - transform.position.y;

        if (diferencia > 15)
        {
            for(int i = 0; i < this.transform.childCount; i++)
            {
                GameObject go = this.transform.GetChild(i).gameObject;
                if(go.name=="Demon" || go.name == "MaloAvanza")
                {
                    this.gm.enemigos.Remove(go);
                }
            }
            Destroy(this.gameObject);
        }
	}

    void OnTriggerEnter(Collider other)
    {
        if (other.name == "MC"&& !fichado)
        {
            if(orden == ordenes-1)
            {
                gm.nextLevel();
                fichado = true;

            }
        }
    }

    public static GameObject generarNivel(int posicion, GameManager gm)
    {
        int rand = Random.Range(1, 4);
        string path = "Prefabs/lvl_0" + rand;

        GameObject prefab = Resources.Load(path) as GameObject;

        float posY = 8.5f * posicion;
        Debug.Log("nombre:"+prefab.name);

        GameObject nivelObject = (GameObject)Instantiate(prefab, Vector3.zero+ new Vector3(0,posY), Quaternion.identity);

        for (int i = 0; i < nivelObject.transform.childCount; i++)
        {
            if(nivelObject.transform.GetChild(i).name == "Demon" || nivelObject.transform.GetChild(i).name == "MaloAvanza" )
            {
                gm.enemigos.Add(nivelObject.transform.GetChild(i).gameObject);
                if (nivelObject.transform.GetChild(i).name == "Demon")
                {
                    nivelObject.transform.GetChild(i).gameObject.GetComponent<DemonController>().gm = gm;
                }
            }
        }
        prefab.GetComponent<LvlController>().gm = gm;
        return prefab;
    }

}
