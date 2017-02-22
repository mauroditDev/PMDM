﻿using UnityEngine;
using System.Collections;
using System;

public class GameManager : MonoBehaviour {
    public GameObject masterChief;
    public GameObject[] enemigos;
    public LvlController[] niveles;
	// Use this for initialization
	void Start () {

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
        LvlController[] nivAux = new LvlController[niveles.Length];
        niveles = new LvlController[niveles.Length + 1];

    }
}
