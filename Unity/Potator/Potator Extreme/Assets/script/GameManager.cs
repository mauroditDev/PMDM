using UnityEngine;
using System.Collections.Generic;
using System;

public class GameManager : MonoBehaviour {
    public GameObject masterChief;
    public List<GameObject> enemigos;
    public List<GameObject> niveles;
    private int nivelMax;
	// Use this for initialization
	void Start () {
        for (nivelMax = 0; nivelMax<3;nivelMax++)
        {
            niveles.Add(LvlController.generarNivel(nivelMax,this));
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
        niveles.RemoveAt(0);
        niveles.Add(LvlController.generarNivel(nivelMax++, this));
        /*
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
                niveles[i] = niveles[i].generarNivel(nivelMax++);
                niveles[i].orden = i;
            }
        }
        */
    }
    
}
