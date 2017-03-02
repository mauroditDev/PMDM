using UnityEngine;
using System.Collections.Generic;
using System;

public class GameManager : MonoBehaviour {
    public GameObject masterChief;
    public List<GameObject> enemigos;
    public LvlController[] niveles;
    private int nivelMax;
	// Use this for initialization
	void Start () {
        niveles = new LvlController[3];
        nivelMax = 0;
        foreach (LvlController niv in niveles)
        {
            niveles[nivelMax] = new LvlController();
            niveles[nivelMax].gm = this;
            Debug.Log("el gm del nivel " + nivelMax + " es = " + niveles[nivelMax].gm.name);
            niveles[nivelMax] = niveles[nivelMax].generarNivel(nivelMax);
            nivelMax++;
            Debug.Log(nivelMax);
        }
        
	}
	
	// Update is called once per frame
	void Update () {
       

    }

    public void disparar(bool derecha, float posX, float posY)
    {
        float min = posY - 0.3f; float max = posY + 0.3f;
        
        foreach (GameObject enemigo in enemigos){
            float myY = enemigo.transform.position.y;

            if (derecha)
            {
                if ( myY > min && myY < max && enemigo.transform.position.x < posX)
                {
                    if (enemigo.tag == "Demon")
                    {
                        enemigo.GetComponent<DemonController>().hits++;
                    }
                    if(enemigo.tag == "Bestia")
                    {
                        enemigo.GetComponent<BestiaController>().hits++;
                    }
                }
            }
            else
            {
                if (myY > min && myY < max && enemigo.transform.position.x > posX)
                {
                    if (enemigo.tag == "Demon")
                    {
                        enemigo.GetComponent<DemonController>().hits++;
                    }
                    if (enemigo.tag == "Bestia")
                    {
                        enemigo.GetComponent<BestiaController>().hits++;
                    }
                }
            }
            
        }
    }

   
    public void nextLevel()
    {
        LvlController[] nivAux = niveles;
        niveles = new LvlController[niveles.Length];
        for(int i = 0; i< niveles.Length; i++)
        {
            if (i < niveles.Length - 1)
            {
                niveles[i] = nivAux[i + 1];
            }
            else
            {
                niveles[i] = new LvlController();
                niveles[i].gm = this;
                niveles[i].generarNivel(nivelMax++);
            }
        }

    }
    
}
